package az.sudo.mail.auth

import az.sudo.mail.BuildConfig
import net.thunderbird.core.common.oauth.OAuthConfiguration
import net.thunderbird.core.common.oauth.OAuthConfigurationFactory

@Suppress("ktlint:standard:max-line-length")
class TbOAuthConfigurationFactory : OAuthConfigurationFactory {
    override fun createConfigurations(): Map<List<String>, OAuthConfiguration> {
        return mapOf(
            createAolConfiguration(),
            createGmailConfiguration(),
            createFastmailConfiguration(),
            createMicrosoftConfiguration(),
            createYahooConfiguration(),
        )
    }

    private fun createAolConfiguration(): Pair<List<String>, OAuthConfiguration> {
        return listOf(
            "imap.aol.com",
            "smtp.aol.com",
        ) to OAuthConfiguration(
            clientId = "dj0yJmk9QTVYM0I0RGQ0VDFBJmQ9WVdrOVNYcFphVWRVYVhnbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTA1",
            scopes = listOf("mail-w"),
            authorizationEndpoint = "https://api.login.aol.com/oauth2/request_auth",
            tokenEndpoint = "https://api.login.aol.com/oauth2/get_token",
            redirectUri = "${BuildConfig.APPLICATION_ID}://oauth2redirect",
        )
    }

    private fun createFastmailConfiguration(): Pair<List<String>, OAuthConfiguration> {
        return listOf(
            "imap.fastmail.com",
            "smtp.fastmail.com",
        ) to OAuthConfiguration(
            clientId = "353e41ae",
            scopes = listOf("https://www.fastmail.com/dev/protocol-imap", "https://www.fastmail.com/dev/protocol-smtp"),
            authorizationEndpoint = "https://api.fastmail.com/oauth/authorize",
            tokenEndpoint = "https://api.fastmail.com/oauth/refresh",
            redirectUri = "${BuildConfig.APPLICATION_ID}://oauth2redirect",
        )
    }

    private fun createGmailConfiguration(): Pair<List<String>, OAuthConfiguration> {
        return listOf(
            "imap.gmail.com",
            "imap.googlemail.com",
            "smtp.gmail.com",
            "smtp.googlemail.com",
        ) to OAuthConfiguration(
            clientId = "560629489500-kta4qnt6vrf1bj8ljcohj7cvbbqguauf.apps.googleusercontent.com",
            scopes = listOf("https://mail.google.com/"),
            authorizationEndpoint = "https://accounts.google.com/o/oauth2/v2/auth",
            tokenEndpoint = "https://oauth2.googleapis.com/token",
            redirectUri = "${BuildConfig.APPLICATION_ID}:/oauth2redirect",
        )
    }

    private fun createMicrosoftConfiguration(): Pair<List<String>, OAuthConfiguration> {
        return listOf(
            "outlook.office365.com",
            "smtp.office365.com",
        ) to OAuthConfiguration(
            clientId = "e6f8716e-299d-4ed9-bbf3-453f192f44e5",
            scopes = listOf(
                "openid",
                "email",
                "https://outlook.office.com/IMAP.AccessAsUser.All",
                "https://outlook.office.com/SMTP.Send",
                "offline_access",
            ),
            authorizationEndpoint = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize",
            tokenEndpoint = "https://login.microsoftonline.com/common/oauth2/v2.0/token",
	    redirectUri = "msauth://az.sudo.mail/S9nqeF27sTJcEfaInpC%2BDHzHuCY%3D"
        )
    }

    private fun createYahooConfiguration(): Pair<List<String>, OAuthConfiguration> {
        return listOf(
            "imap.mail.yahoo.com",
            "smtp.mail.yahoo.com",
        ) to OAuthConfiguration(
            clientId = "dj0yJmk9SW1VbkV0R0FJYU1mJmQ9WVdrOU1Hc3dlSEp3YzNFbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWNk",
            scopes = listOf("mail-w"),
            authorizationEndpoint = "https://api.login.yahoo.com/oauth2/request_auth",
            tokenEndpoint = "https://api.login.yahoo.com/oauth2/get_token",
            redirectUri = "${BuildConfig.APPLICATION_ID}://oauth2redirect",
        )
    }
}
