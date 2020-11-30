package click.seichi.oneactiontp.util

import click.seichi.oneactiontp.config.Message
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

fun hoverAcceptText(senderName: String) = TextComponent(Message.clickToAccept).apply {
    this.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(Message.hoverToAccept).create())
    this.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppaccept $senderName")
}

fun hoverDenyText(senderName: String) = TextComponent(Message.clickToDeny).apply {
    this.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(Message.hoverToDeny).create())
    this.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppdeny $senderName")
}

val spaceText = TextComponent("     ")