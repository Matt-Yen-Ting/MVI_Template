package com.example.features.announcement

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.designsystem.commonview.BackPreviousIcon
import com.example.core.designsystem.commonview.MainTopBar
import com.example.data.commondata.navigation.Screen
import com.example.core.R
import com.example.features.announcement.state.AnnouncementState

@Composable
fun AnnouncementScreen(
    navHostController: NavHostController,
    viewModel: AnnouncementViewModel = hiltViewModel()
) {
    val announcementState by viewModel.announcementState.collectAsState(AnnouncementState())
    AnnouncementScreenContent(
        navHostController,
        announcementState
    ) { intent ->
        viewModel.sendIntent(intent)
    }

}

@Composable
fun AnnouncementScreenContent(
    navHostController: NavHostController,
    getAnnouncementState: AnnouncementState,
    sendIntent: (AnnouncementIntent) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                navigationIcon = {
                    BackPreviousIcon {
                        navHostController.popBackStack()
                    }
                },
                title = {
                    Text(stringResource(R.string.announcement_page))
                }
            )
        }
    ) { contentPadding ->
        BackHandler {
            navHostController.popBackStack()
        }
        Column(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding(), bottom =if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM)  contentPadding.calculateBottomPadding() else 0.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.fillMaxSize()) {
                    HandleGetDataListUiState(navHostController, getAnnouncementState, sendIntent)
                }
                if (getAnnouncementState.showLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun HandleGetDataListUiState(
    navHostController: NavHostController,
    announcementState: AnnouncementState,
    sendIntent: (AnnouncementIntent) -> Unit,
) {
    val context = LocalContext.current
    when {
        announcementState.getDataSuccess -> {
            if (announcementState.dataList.isNotEmpty()) {
                LazyColumn(Modifier.fillMaxHeight()) {
                    items(announcementState.dataList) {
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .border(
                                    BorderStroke(2.dp, Color.Black)
                                )
                                .clickable {
                                    navHostController.navigate(Screen.AnnouncementDetail(it.title))
                                }
                        ) {
                            Column(modifier = Modifier.padding(3.dp)) {
                                Text(it.posted, modifier = Modifier.padding(3.dp))
                                Text(it.title)
                            }
                        }
                    }
                }
            }

        }

        announcementState.getDataFail -> {
            Toast.makeText(
                context,
                stringResource(R.string.failed_to_obtain_list_information),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Preview
@Composable
fun AnnouncementScreenContentPreview() {
    AnnouncementScreenContent(rememberNavController(), AnnouncementState()) { }
}




