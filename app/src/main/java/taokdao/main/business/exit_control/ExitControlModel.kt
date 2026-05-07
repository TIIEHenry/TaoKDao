package taokdao.main.business.exit_control

class ExitControlModel : ExitControlContract.M {


    private var mExitTime: Long = 0
    override fun catchDoubleBack(): Boolean {
        return when {
            (System.currentTimeMillis() - mExitTime) > 2000 -> {
                mExitTime = System.currentTimeMillis()
                false
            }
            else -> true
        }
    }
}
