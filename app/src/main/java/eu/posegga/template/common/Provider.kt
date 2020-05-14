package eu.posegga.template.common

interface Provider<TYPE> {

    fun provide(): TYPE
}
