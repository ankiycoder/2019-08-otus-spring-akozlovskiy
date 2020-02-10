$(function() {
	$.get('/book').done(function (books) {
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


$(function() {
	  $("button#updateBookButton").click(
	    function(data, status){
	      alert("Data: " + data + "\nStatus: " + status);
	    });
	
	});

$(function() {
	  $("button#addBookButton").click(function(){
	    $.post("http://localhost:8080/book/save", $('#addBookForm').serialize(),
	    function(data, status){
	      alert("Data: " + data + "\nStatus: " + status);
	    });
	  });
	});

$(document).ready(function(){
	  $("#updateGenreButton").click(function(){
	    $.post("http://localhost:8080/genre/update", $('#updateGenreForm').serialize(),
	    function(data, status){
	      alert("Data: " + data + "\nStatus: " + status);
	    });
	  });
	});