package com.hltech.judged.contracts.publisher.standalone

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.associate
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.hltech.judged.contracts.publisher.Publisher
import com.hltech.judged.contracts.publisher.PublisherProperties

class PublishContracts : CliktCommand() {
    val serviceName: String by option("-sn", "--service-name", help = "service name for which your contracts will be published").prompt("Service name")
    val serviceVersion: String by option("-sv", "--service-version", help = "service version for which your contracts will be published").prompt("Service version")
    val judgeDLocation: String by option("-jd", "--judge-d-location", help = "URL of judge-d instance to which your contracts will be uploaded").prompt("Judge-D location url")
    val capabilities: List<String> by option("-cb", "--capabilities", help = "capabilities of your service, could be specified multiple times").multiple()
    val expectations: List<String> by option("-ex", "--expectations", help = "expectations of your service, could be specified multiple times").multiple()
    val extras: Map<String, String> by option("-D", help = "additional properties provided in key-value form").associate()

    override fun run() {
        Publisher().publish(PublisherProperties(serviceName, serviceVersion, judgeDLocation, capabilities, expectations, extras))
    }
}

fun main(args: Array<String>) = PublishContracts().main(args)
