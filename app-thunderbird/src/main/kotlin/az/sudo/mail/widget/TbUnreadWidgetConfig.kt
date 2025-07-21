package az.sudo.mail.widget

import app.k9mail.feature.widget.unread.UnreadWidgetConfig
import az.sudo.mail.widget.provider.UnreadWidgetProvider

class TbUnreadWidgetConfig : UnreadWidgetConfig {
    override val providerClass = UnreadWidgetProvider::class.java
}
