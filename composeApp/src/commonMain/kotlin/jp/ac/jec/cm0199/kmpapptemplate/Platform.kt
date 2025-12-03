package jp.ac.jec.cm0199.kmpapptemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform