# MVI-ARCHITECTURE

## MVI架構簡要說明
1. View層(Activity、Fragment、Compose)，透過Intent action 發號施令，並觀察資料流變化做UI改動
2. ViewModel層接受並執行指令，最後完成結果透過Reactive data響應給View層
3. Model層(Repository)負責資料處理及運算

## 使用MVI的理由
1. 低耦合度
2. 利於維護
3. 較多人討論的主流架構，可參考資源多
4. 有利團隊開發，討論問題時比較能夠聚焦
5. 資料與View分層維護，減少維護及開發時間

## 專案基礎架構
1. Reactive Data
2. Kotlin Coroutine(Flow、Channel)，MVVM、MVI的主要精髓，把資料看成流的概念
3. ViewModel(View及Model的媒介)
4. 單Activity(整個App 只會有一個MainActivity)，多Fragment
5. JetPack Navigation 切換頁面、頁面間傳遞參數
6. JetPack DataStore 處理小資料儲存
7. JetPack Hilt 處理Repository、Api、NetWork等單例或複用，可以大量減少樣板程式碼
8. 命名Class 採大駝峰、function 採小駝峰
9. JetPack Compose 代替XML layout,每個頁面以Screen結尾
10. 有關Ui的相關變化都寫在Fragment(View)
11. 有關Api、資料存取、都在ViewModel

## 使用JetPack的理由
1. Android 官方建議
2. 官方持續支援
3. 經過官方及多方彙整及修正
4. 減少樣板程式碼及程式碼撰寫數量

### 有用到的Android JetPack Library
1. Android KTX
2. Navigation
3. Hilt(DI)，此項可討論是否改用Koin
4. DataStore
5. View binding

### 有用到的Third Party
1. Retrofit
2. Okhttp
3. Moshi

### 需討論的問題
1. 是否要淘汰XML，改用Compose?
2. 是否使用模組化分類各個頁面?(可參考:https://github.com/android/nowinandroid)
3. Code Review 需要Review的內容?