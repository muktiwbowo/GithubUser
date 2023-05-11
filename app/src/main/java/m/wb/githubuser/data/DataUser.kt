package m.wb.githubuser.data

import m.wb.githubuser.R

data class DataUser(
    val name: String,
    val username: String,
    val avatar: Int = R.drawable.ic_launcher_background
)

val dummyUsers = listOf(DataUser(name = "John One", "johndoeone"),
    DataUser(name = "John Two", "johndoetwo"),
    DataUser(name = "John Three", "johndoethree"),
    DataUser(name = "John Four", "johndoefour"),
    DataUser(name = "John Five", "johndoefive"))