package com.sunfusheng.dagger2.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var user: User
    @Inject
    lateinit var repo: Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName = "sunfusheng"
        val repoName = "MarqueeView"

//        DaggerMainComponent.builder()
//            .userModule(UserModule(userName))
//            .repoModule(RepoModule(repoName))
//            .build()
//            .inject(this);
//
//        user.info()
//        repo.info()

        val user = User("sunfusheng")

        val provider:IOwnerProvider = Repo("GitHub")
        provider.setOwner(user)

        val repo = Repo("GitHub")
        repo.setOwner(user)

        Log.d("--->", user.toString())
        Log.d("--->", repo.toString())
    }
}


data class User constructor(val userName: String)

interface IOwnerProvider {
    fun setOwner(owner: User)
}

data class Repo constructor(val repoName: String) : IOwnerProvider {
    lateinit var user: User

    override fun setOwner(owner: User) {
        this.user = owner
    }
}

