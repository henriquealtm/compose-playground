package com.example.composeplayground.navigation

import com.example.composeplayground.commons.navigation.deeplink.IDeeplinkProcessor
import com.example.composeplayground.ticketing.ticket.navigation.TicketDeeplinkProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface DeepLinkProcessorModule {

    @Binds
    @IntoSet
    fun bindsTicketDeeplinkProcessor(
        ticketDeeplinkProcessor: TicketDeeplinkProcessor
    ): IDeeplinkProcessor

}
