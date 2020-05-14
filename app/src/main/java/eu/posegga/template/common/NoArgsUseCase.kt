package eu.posegga.template.common

interface NoArgsUseCase<RETURN> {

    fun execute(): RETURN
}
