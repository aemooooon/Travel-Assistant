//[app](../../../index.md)/[op.mobile.project.api](../index.md)/[TranslatorApi](index.md)/[getTranslator](get-translator.md)



# getTranslator  
[androidJvm]  
Content  
@GET(value = api/v1.5/tr.json/translate)  
  
abstract suspend fun [getTranslator](get-translator.md)(@Query(value = key)key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = text)text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = lang)lang: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Response<[Translator](../../op.mobile.project.model/-translator/index.md)>  
More info  


#### Return  


: Translator Object



## Parameters  
  
androidJvm  
  
| | |
|---|---|
| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a>key| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>: Yandex Api Key<br><br>|
| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a>text| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>: text to translate type: String<br><br>|
| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a>lang| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>: language from to type: String<br><br>|
  
  



