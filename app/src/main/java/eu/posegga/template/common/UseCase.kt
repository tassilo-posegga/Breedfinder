package eu.posegga.template.common

interface UseCase<PARAMS, RETURN> {

    fun execute(params: PARAMS): RETURN
}
