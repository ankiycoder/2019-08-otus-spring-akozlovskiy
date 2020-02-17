$(function() {
	$.get('/book').done(function (books) {
       	books.forEach(function (book) {
       		 $("table#bookTable").append(`
                   <tr>
                       <td>${book.title}</td>
                       <td>${book.authorName}</td>
                       <td>${book.genre}</td>
                       <td width='0*'>                       				 		
       				 	 	<button id="updateBookButton" type="submit" class="btn btn-success">Изменить книгу</button>                        
       				 	</td>
                   </tr>
               `) 
           }); 
       })
   });

$(document).delegate('#updateBookButton', 'click', function() {
	var parent = $(this).parent().parent();
	
	var title = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");
	var buttons = parent.children("td:nth-child(4)");
	
	title.html("<input type='text' id='title' value='" + title.html() + "'/>");
	buttons.html("<button id='saveBookButton' type='submit' class='btn btn-success'>Save</button>&nbsp;&nbsp;<button class='delete' id='" + title.html() + "'>Delete</button>");
});


$(document).delegate('#saveBookButton', 'click', function() {
	var parent = $(this).parent().parent();
	
	var title = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");
	var buttons = parent.children("td:nth-child(4)");
	
	title.html(title.children("input[type=text]").val());
	buttons.html("<button id='updateBookButton' type='submit' class='btn btn-success'>Изменить книгу</button>")  
});

var updateBook = function(id) {
	  window.location.href = "updateBook/"+id;
}