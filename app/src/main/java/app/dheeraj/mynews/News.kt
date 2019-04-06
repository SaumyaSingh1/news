package app.dheeraj.mynews

import java.security.CodeSource

class Newsresponse(var status :String,
                   var total :Int,
                   var articles : List<News>)
class News(
     var source: Source,
     val newsId: Int,
     var author: String?,
     var title: String?,
     var description: String?,
     var url: String?,
     var urlToImage: String?,
     var publishedAt: String?,
     var content: String?,
     var type : String?
)
class Source(
    var id: String?,
    var name: String?
)
