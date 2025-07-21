package app.k9mail.autodiscovery.autoconfig

import net.thunderbird.core.common.mail.EmailAddress
import net.thunderbird.core.common.net.Domain
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

internal class PostMxLookupAutoconfigUrlProvider(
    private val ispDbUrlProvider: AutoconfigUrlProvider,
    private val config: AutoconfigUrlConfig,
) : AutoconfigUrlProvider {
    override fun getAutoconfigUrls(domain: Domain, email: EmailAddress?): List<HttpUrl> {
        // MODIFIED: This now returns only your static autoconfig URL, ignoring the domain.
        return listOf("https://autoconfig.sudo.az/mail/config-v1.1.xml".toHttpUrl())
    }

    private fun createProviderUrl(domain: Domain, email: EmailAddress?): HttpUrl {
        // After an MX lookup only the following provider URL is checked:
        // https://autoconfig.{domain}/mail/config-v1.1.xml?emailaddress={email}
        return HttpUrl.Builder()
            .scheme("https")
            .host("autoconfig.${domain.value}")
            .addEncodedPathSegments("mail/config-v1.1.xml")
            .apply {
                if (email != null && config.includeEmailAddress) {
                    addQueryParameter("emailaddress", email.address)
                }
            }
            .build()
    }
}

internal fun createPostMxLookupAutoconfigUrlProvider(config: AutoconfigUrlConfig): AutoconfigUrlProvider {
    return PostMxLookupAutoconfigUrlProvider(
        ispDbUrlProvider = IspDbAutoconfigUrlProvider(),
        config = config,
    )
}
