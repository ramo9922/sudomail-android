package az.sudo.mail.widget

import app.k9mail.feature.widget.message.list.MessageListWidgetConfig
import az.sudo.mail.widget.provider.MessageListWidgetProvider

class TbMessageListWidgetConfig : MessageListWidgetConfig {
    override val providerClass = MessageListWidgetProvider::class.java
}
