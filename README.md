# MVI-ARCHITECTURE

## 資料夾分類結構圖

Project/
├─ build.gradle.kts
├─ settings.gradle.kts
├─ gradle/                 # 共用腳本
├─ core/ 
│  ├─ design-system/       # Compose theme、共用元件(Ex:MainTopBar)
│  ├─ di/
│  ├─ extension/           # 時間格式轉換、數字類別轉換..等
│  ├─ network/             # Retrofit & OkHttp 封裝
│  ├─ utils/               # 日期、加解密、Result 包裝
│  └─ websocket/           # OkHttp WebSocket + SharedFlow
├─ domain/
│  ├─ di/                  # Repository Module
│  ├─ model/               # Entity
│  ├─ repository/          # 底下再照各類別商業邏輯拆分資料夾(Ex:account、announcement)
│  └─ usecase/             # UseCase、UseCase Implementation、底下再照各類別商業邏輯拆分資料夾(Ex:account、announcement)
├─ data/
│  ├─ common_data/         # 全域共用Data(Ex:各個Screen navigation Route)
│  └─ datasource/          # Room, DataStore
├─ features/
│  ├─ login/
│  │  ├─ intent/           # 觸發相關事件、發送給ViewModel 執行邏輯
│  │  ├─ state/            # Ui變化的State
│  │  ├─ reducer/          # 繼承共用Reducer
│  │  ├─ ui/               # Jetpack Compose
│  │  └─ di/
│  ├─ home/
│  │  ├─ intent/
│  │  ├─ state/
│  │  ├─ reducer/
│  │  ├─ ui/               # Jetpack Compose
│  │  └─ di/
│  └─ …                    # 其餘功能同格式
├─               
└─ app/                   # 包含Navigation 的NavGraph、全App唯一一個Application
   └─ main/               # MainActivity、MainIntent、MainViewModel、各State


## 專案基礎架構
1. JetPack Compose 每個頁面以Screen結尾
2. 統一在NavGraph file 新增要導航的 composable、跟設定要傳遞的參數
3. Kotlin Coroutine(StateFlow)
4. ViewModel(View及Model的媒介)、透過UseCase 與資料來源(Room、DataStore、Api)互動、Reduce更新UiState
5. 全App只會有一個App(Application)、Activity(MainActivity)
6. JetPack DataStore 處理小資料儲存 
7. JetPack Hilt 處理Repository、Api、NetWork等單例或複用 
8. 命名Class 採大駝峰、function 採小駝峰
9. 無法透過Compose實作、一定要用舊的View，透過Android View引入Compose
10. 需要Context 透過Local.current取得
11. Ui變化請透過State

### 有用到的Android JetPack Library
1. Android KTX
2. Navigation
3. Hilt(DI)，此項可討論是否改用Koin
4. DataStore
5. Compose

### 有用到的Third Party
1. Retrofit
2. Okhttp
3. Moshi
4. Coil

## 實作商業邏輯架構簡要說明
1. View層(Compose)，透過Intent action 觸發相關事件、發送給ViewModel 執行邏輯，並觀察資料流變化做UI改動
2. ViewModel層接受並執行指令，Reducer 更新相對應的UiState，State 透過Coroutine StateFlow 響應
3. Composable Screen 觀察State 變化，透過CollectAsStateWithLifecycle() 把StateFlow 轉換成State，並透過觀察State 變動相關Ui