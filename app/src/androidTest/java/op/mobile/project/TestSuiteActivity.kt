package op.mobile.project

import org.junit.runner.RunWith
import org.junit.runners.Suite

// Declare a Suite class which could run all test class in once
@RunWith(Suite::class)
@Suite.SuiteClasses(
    TestTranslatorFragmentInView::class,
    TestAlertDialog::class,
    TestExitApp::class,
    TestFragmentNavigation::class,
    TestIndexActivity::class,
    TestMainActivity::class,
    TestNavigateFromMainActivityToIndexActivity::class,
    TestSplashScreenActivity::class,
    TestThemeChange::class,
    TestTranslatorCopyright::class,
    TestTranslatorEnterText::class,
    TestTranslatorChangeLanguages::class,
    TestPhrasesRecyclerView::class,
    TestTextToSpeechFragmentInView::class,
    TestTextToSpeechFragment::class
)
class TestSuiteActivity {
}