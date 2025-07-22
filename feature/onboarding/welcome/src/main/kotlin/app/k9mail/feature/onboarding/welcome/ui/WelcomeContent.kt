package app.k9mail.feature.onboarding.welcome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.k9mail.core.ui.compose.designsystem.atom.Surface
import app.k9mail.core.ui.compose.designsystem.atom.button.ButtonFilled
import app.k9mail.core.ui.compose.designsystem.atom.text.TextDisplayMedium
import app.k9mail.core.ui.compose.designsystem.template.ResponsiveContent
import app.k9mail.core.ui.compose.theme2.MainTheme
import app.k9mail.feature.onboarding.welcome.R
import net.thunderbird.core.ui.compose.common.modifier.testTagAsResourceId

private const val CIRCLE_COLOR = 0xFFEEEEEE
private const val LOGO_SIZE_DP = 125

@Composable
internal fun WelcomeContent(
    onStartClick: () -> Unit,
    onImportClick: () -> Unit,
    appName: String,
    showImportButton: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
    ) {
        ResponsiveContent {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                // This Column holds the logo and title and is aligned to the TOP
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 64.dp), // Adds space from the status bar
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    WelcomeLogo()
                    WelcomeTitle(title = appName)
                }

                // This aligns the button to the bottom, then pushes it up with padding
                WelcomeFooter(
                    onStartClick = onStartClick,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 128.dp), // << CHANGE THIS VALUE TO TWEAK POSITION
                )
            }
        }
    }
}

@Composable
private fun WelcomeLogo(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color(CIRCLE_COLOR))
            .size(150.dp),
    ) {
        Image(
            painter = painterResource(id = MainTheme.images.logo),
            contentDescription = null,
            modifier = Modifier
                .size(LOGO_SIZE_DP.dp)
                .align(Alignment.Center),
        )
    }
}

@Composable
private fun WelcomeTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    TextDisplayMedium(
        text = title,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(top = MainTheme.spacings.double),
    )
}

@Composable
private fun WelcomeFooter(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ButtonFilled(
        text = stringResource(id = R.string.onboarding_welcome_start_button),
        onClick = onStartClick,
        modifier = modifier.testTagAsResourceId("onboarding_welcome_start_button"),
    )
}
