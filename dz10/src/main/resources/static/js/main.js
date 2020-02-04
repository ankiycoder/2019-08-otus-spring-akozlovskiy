$(function() {
	$.get('/api/book').done(function (books) {
       	books.forEach(function (book) {
       		 $("table#bookTable").append(`
                   <tr>
                       <td>${book.title}</td>
                       <td>${book.authorName}</td>
                       <td>${book.genre}</td>
                       <td width='0*'>                
       				 		<a href="#" onClick="updateBook(${book.id})" class="btn btn-info">Изменить</a>        
       				 	</td>
                   </tr>
               `) 
           }); 
       })
   });


var updateBook = function(id) {
	  window.location.href = "updateBook/"+id;
}