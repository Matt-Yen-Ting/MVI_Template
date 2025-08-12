# MVI-ARCHITECTURE

## 資料夾分類結構圖

project/
├─ build.gradle.kts
├─ settings.gradle.kts
├─ gradle/                 # 共用腳本
├─ core/
│  ├─ designsystem/       # Compose theme、共用元件(Ex:MainTopBar)
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
│  ├─ commondata/         # 全域共用Data(Ex:各個Screen navigation Route)
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

## 編寫程式相關規則

### View相關
1. 全App只有一個App(Application)、Activity(MainActivity)
2. JetPack Compose 每個頁面以Screen結尾
3. 一個種類的頁面在feature module package 創建相關package Ex:home、login
4. 命名Class 採大駝峰、function 採小駝峰
5. 需要Context 透過LocalContext.current取得
6. 無法透過Compose實作、需要用舊的View，透過Android View引入Compose
7. Ui變化透過State
8. values strings等檔案引用Core Module 內的values，引用「R.」時需特別注意
9. Color 顏色用 Core Module內design_system theme package 的Color file
10. Theme 用 Core Module內design_system theme package 的Theme file
11. Shape 用 Core Module內design_system theme package Shape file
12. 全App共用View 放在 Core Module內designsystem commonview package
13. drawable放 Core Module內 resource drawable內
14. Text 文字統一抽離放string resources

### ViewModel相關
1. ViewModel class 統一用ViewModel結尾，Ex:LoginViewModel
2. 一個頁面一個ViewModel
3. ViewModel 觸發Intent 統一 function name sendIntent
4. StateFlow 統一使用update{ } 更新Reducer
5. 每個Screen一個State
6. Reducer 繼承共用Reducer實作更新State的Reducer

### Model相關
1. 資料邏輯interface、資料邏輯interface implementation 放 Repository，並建立相關類別資料夾
2. 資料邏輯interface implementation以Impl結尾
3. UseCase以類別產生data class
4. UseCase底下各功能 時以suspend operator fun invoke() 創建．並建立類別功能資料夾分類
5. Api 解析data、轉換data放 domain Module model entities package
6. 共用Data 放 data module commondata package，Ex:Navigation Screen route sealed class

### Api相關
1. Api Retrofit Okhttp 等封裝放Core Module network
2. Api Repository interface 放 domain Module repository api package


### 有用到的Android JetPack Library
1. Android KTX
2. Navigation
3. Hilt(DI)
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