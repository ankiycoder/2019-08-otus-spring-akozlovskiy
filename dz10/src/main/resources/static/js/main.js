$(document).ready(function() {
	$("#bookTable").get("/api/book").done(function (books) {
       	books.forEach(function (book) {
               $("tbody").append(`
                   <tr>
                       <td>${book.title}</td>
                       <td>${book.authorName}</td>
                       <td>${book.genre}</td>
                       <td width='0*'><a
						th:href="@{/updateBook/{id}(id=${book.id})}" class="btn btn-info">Изменить</a>
					</td>
                   </tr>
               `)
           });
       })
   });

$(document).ready(function() {
	$("#demo").html("Hello, World!");
	});