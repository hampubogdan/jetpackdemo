package com.hampu.jetpackdemo

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.fragment.findNavController
import com.hampu.jetpackdemo.db.AppDatabase
import com.hampu.jetpackdemo.db.DatabaseCreator
import com.hampu.jetpackdemo.db.dao.UserDao
import com.hampu.jetpackdemo.db.viewmodel.RxUserViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.list_fragment.*

/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
class ListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }

    var appDatabase: AppDatabase? = null
    var userDao: UserDao? = null

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.settingsFragment -> {
                findNavController().navigate(R.id.settingsFragment)
            }
            R.id.menuitem2 -> {
                val bundle = Bundle()
                bundle.putInt("itemId", 145)
                findNavController().navigate(R.id.detailsFragment, bundle)
            }
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = Room.databaseBuilder(context!!, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
        userDao = appDatabase!!.userDao()

        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm

        val viewModel = ViewModelProviders.of(this).get(RxUserViewModel::class.java)
        viewModel.init(userDao!!)

        val userAdapter = UserAdapter {
            val action = ListFragmentDirections.detailsAction()
            action.setItemId(it)
            findNavController().navigate(action)
        }
        settingsButton.setOnClickListener {
            val action = ListFragmentDirections.settingsAction()
            action.setParam1("TEST")
            action.setParam2("Test2")
            findNavController().navigate(action)
        }
        inserUserButton.setOnClickListener {
            insertUsers(appDatabase!!)
        }
        deleteUsersButton.setOnClickListener {
            deleteUsers(appDatabase!!)
        }


        //LIVE DATA
        /* viewModel.userList.observe(this, Observer {
             userAdapter.submitList(it)
         })*/

        //RX
        disposable.add(viewModel.userList.subscribe({ flowableList ->
            userAdapter.submitList(flowableList)
        }))

        recyclerView.adapter = userAdapter
    }

    private fun insertUsers(appDatabase: AppDatabase) {
        val databaseCreator = DatabaseCreator()
        val task = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                appDatabase.userDao().insertAll(databaseCreator.randomUserList)
                return null
            }
        }
        task.execute()
    }

    private fun deleteUsers(appDatabase: AppDatabase) {
        val task = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                appDatabase.userDao().deleteAll()
                return null
            }
        }
        task.execute()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}

