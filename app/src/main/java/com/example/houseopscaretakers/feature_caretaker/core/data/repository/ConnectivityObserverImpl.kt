package com.example.houseopscaretakers.feature_caretaker.core.data.repository

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.houseopscaretakers.feature_caretaker.core.domain.model.ConnectionStatus
import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.ConnectivityObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectivityObserverImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ConnectivityObserver {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun observeConnectivity(): Flow<ConnectionStatus> {

        return callbackFlow {

            //  custom network callback
            val callback = object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectionStatus.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ConnectionStatus.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ConnectionStatus.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ConnectionStatus.Unavailable) }
                }
            }

            //  register our custom callback
            connectivityManager.registerDefaultNetworkCallback(callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}











