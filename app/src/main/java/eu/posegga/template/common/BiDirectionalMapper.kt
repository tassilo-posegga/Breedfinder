package eu.posegga.template.common

interface BiDirectionalMapper<A, B> {

    fun from(input: A): B

    fun to(input: B): A
}
