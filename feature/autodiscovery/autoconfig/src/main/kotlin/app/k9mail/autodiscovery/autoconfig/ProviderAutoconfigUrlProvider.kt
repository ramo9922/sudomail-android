package app.k9mail.autodiscovery.autoconfig

import net.thunderbird.core.common.mail.EmailAddress
import net.thunderbird.core.common.net.Domain
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

internal class ProviderAutoconfigUrlProvider(private val config: AutoconfigUrlConfig) : AutoconfigUrlProvider {
    override fun getAutoconfigUrls(domain: Domain, email: EmailAddress?): List<HttpUrl> {
        // MODIFIED: This now returns only your static autoconfig URL, ignoring the domain.
        return listOf("https://autoconfig.sudo.az/mail/config-v1.1.xml".toHttpUrl())
    }

    private fun createProviderUrl(domain: Domain, email: EmailAddress?, useHttps: Boolean): HttpUrl {
        // https://autoconfig.{domain}/mail/config-v1.1.xml?emailaddress={email}
        // http://autoconfig.{domain}/mail/config-v1.1.xml?emailaddress={email}
        return HttpUrl.Builder()
            .scheme(if (useHttps) "https" else "http")
            .host("autoconfig.${domain.value}")
            .addEncodedPathSegments("mail/config-v1.1.xml")
            .apply {
                if (email != null && config.includeEmailAddress) {
                    addQueryParameter("emailaddress", email.address)
                }
            }
            .build()
    }

    private fun createDomainUrl(domain: Domain, email: EmailAddress?, useHttps: Boolean): HttpUrl {
        // https://{domain}/.well-known/autoconfig/mail/config-v1.1.xml?emailaddress={email}
        // http://{domain}/.well-known/autoconfig/mail/config-v1.1.xml?emailaddress={email}
        return HttpUrl.Builder()
            .scheme(if (useHttps) "https" else "http")
            .host(domain.value)
            .addEncodedPathSegments(".well-known/autoconfig/mail/config-v1.1.xml")
            .apply {
                if (email != null && config.includeEmailAddress) {
                    addQueryParameter("emailaddress", email.address)
                }
            }
            .build()
    }
}

data class AutoconfigUrlConfig(
    val httpsOnly: Boolean,
    val includeEmailAddress: Boolean,
)
