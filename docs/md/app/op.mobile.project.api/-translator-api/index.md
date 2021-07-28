//[app](../../../index.md)/[op.mobile.project.api](../index.md)/[TranslatorApi](index.md)



# TranslatorApi  
 [androidJvm] interface [TranslatorApi](index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a>[getTranslator](get-translator.md)| <a name="op.mobile.project.api/TranslatorApi/getTranslator/#kotlin.String#kotlin.String#kotlin.String/PointingToDeclaration/"></a>[androidJvm]  <br>Content  <br>@GET(value = api/v1.5/tr.json/translate)  <br>  <br>abstract suspend fun [getTranslator](get-translator.md)(@Query(value = key)key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = text)text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = lang)lang: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Response<[Translator](../../op.mobile.project.model/-translator/index.md)>  <br><br><br>|

