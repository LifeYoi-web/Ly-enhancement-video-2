# بطاقات لايف يوي · LifeYoi Cards

تطبيق أندرويد أصلي (Native) لعرض **بطاقات الاشتراكات** بتصميم جذّاب ومتحرك وسريع،
مبني بالكامل بلغة **Kotlin** و **Jetpack Compose** وبهوية لايف يوي البصرية (برتقالي على أسود).

An attractive, animated and fast native Android app for **subscription cards**,
built entirely with **Kotlin + Jetpack Compose**, using the LifeYoi brand
identity (orange on black).

## ✨ المميزات

- 🎬 **شاشة بداية متحركة** بشعار لايف يوي (تكبير + تلاشي).
- 🪄 **كاروسيل للعروض المميزة** يتحرك تلقائياً مع تأثير عمق (scale/alpha) ونقاط مؤشر متحركة.
- 🗂️ **تصنيفات تفاعلية** (بث، ألعاب، موسيقى، إنترنت، تعليم) مع انتقالات لونية ناعمة.
- 💳 **بطاقات اشتراك** بتدرّجات لونية، تقييم، وتأثير ضغط (press scale).
- 📄 **شاشة تفاصيل** بترويسة متدرّجة، قائمة مميزات متحركة، وزر اشتراك ثابت بالأسفل.
- 🔖 **اشتراكاتي** مع حساب الإجمالي الشهري وحالة فارغة أنيقة.
- 🌙 واجهة داكنة بالكامل و **دعم كامل للعربية (RTL)**.
- ⚡ خفيف وسريع — بدون أي خادم، بيانات تجريبية محلية.

## 🧱 التقنيات

| | |
|---|---|
| اللغة | Kotlin 2.0 |
| الواجهة | Jetpack Compose + Material 3 |
| التنقّل | Navigation Compose |
| الحد الأدنى | Android 7.0 (API 24) |
| الهدف | Android 15 (API 35) |

## ▶️ التشغيل

افتح المشروع في **Android Studio** (Ladybug أو أحدث) ثم شغّله على جهاز/محاكي،
أو من سطر الأوامر:

```bash
./gradlew assembleDebug
# الناتج: app/build/outputs/apk/debug/app-debug.apk
```

> ملاحظة: يلزم وجود Android SDK (Platform 35 و Build-Tools 35) محلياً.

## 🤖 بناء APK تلقائياً (CI)

يحتوي المستودع على workflow في `.github/workflows/android.yml` يقوم ببناء
ملف **APK** عند كل push ويرفعه كـ Artifact باسم `lifeyoi-cards-debug-apk`،
ويمكنك تنزيله من تبويب **Actions** في GitHub وتثبيته مباشرة على هاتفك.

## 📁 البنية

```
app/src/main/java/com/lifeyoi/cards/
├── MainActivity.kt
├── data/Models.kt            # نماذج البيانات + بطاقات تجريبية
└── ui/
    ├── AppNavHost.kt         # التنقّل + الحالة + دعم RTL
    ├── theme/                # الألوان والخطوط والثيم
    ├── components/           # الكاروسيل، البطاقة، التصنيفات، عناصر مشتركة
    └── screens/              # البداية، الرئيسية، التفاصيل، اشتراكاتي
```
