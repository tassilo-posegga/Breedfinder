package eu.posegga.template.common

interface Mapper<INPUT, OUTPUT> {

    fun map(input: INPUT): OUTPUT
}
