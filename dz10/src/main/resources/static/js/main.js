$(function() {
	$.get('/api/book').done(function (books) {
       	books.forEach(function (book) {
       		 $("table#bookTable").append(`
                   <tr id="${book.id}">
       				   <td>${book.id}</td>
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
       	$.get('/api/author').done(function (authors) {
       		authors.forEach(function (author) {
       		 $("table#authorTable").append(`
                   <tr id="${author.id}">
       				   <td>${author.id}</td>
                       <td>${author.name}</td>
                       <td>${author.birthDate}</td>
                       <td width='0*'>                       				 		
       				 	 	<button id="updateAuthorButton" type="submit" class="btn btn-success">Изменить автора</button>                        
       				 	</td>
                   </tr>
               `) 
           }); 
       })
       
      $.get('/api/genre').done(function (genres) {
    	  genres.forEach(function (genre) {
       		 $("table#genreTable").append(`
                   <tr id="${genre.id}">
       				   <td>${genre.id}</td>
                       <td>${genre.description}</td>                   
                       <td width='0*'>                       				 		
       				 	 	<button id="updateGenreButton" type="submit" class="btn btn-success">Изменить жанр</button>                        
       				 	</td>
                   </tr>
               `) 
           }); 
       })
   });

$(document).delegate('#updateBookButton', 'click', function() {
	var parent = $(this).parent().parent();
	
	var title = parent.children("td:nth-child(2)");
	var author = parent.children("td:nth-child(3)");
	var genre = parent.children("td:nth-child(4)");
	var buttons = parent.children("td:nth-child(5)");
	
	var genreSelect = $('<select name="options" id="genreSelect">test</select>');	 	 
		$.get('/api/genre').done(function (genres) {
			genres.forEach(function (genre) {	       		
				genreSelect.append(`<option value="${genre.id}">${genre.description}</option>`);
	           }); 
	       })	       
	genre.html(genreSelect);
		
	var authorSelect = $('<select name="options" id="authorSelect">test</select>');	 	 
		$.get('/api/author').done(function (authors) {
			authors.forEach(function (author) {	       		
				authorSelect.append(`<option value="${author.id}">${author.name}</option>`);
	           }); 
	       })	       
	author.html(authorSelect);		
	title.html("<input type='text' id='title' value='" + title.html() + "'/>");	
	buttons.html("<button id='saveBookButton' type='submit' class='btn btn-success'>Save</button>&nbsp;&nbsp;<button class='btn btn-danger' id='deleteBookButton'>Delete</button>");
});


$(document).delegate('#saveBookButton', 'click', function() {
	var parent = $(this).parent().parent();
		
	var id = parent.children("td:nth-child(1)");
	var title = parent.children("td:nth-child(2)");
	var author = parent.children("td:nth-child(3)");
	var genre = parent.children("td:nth-child(4)");
	var buttons = parent.children("td:nth-child(5)");	
	
	var data = {}
	data["id"] = id.html();
	data["title"] = title.children("input[type=text]").val();
	data["genreId"] = $('#genreSelect option:selected').val();
	data["authorId"] = $('#authorSelect option:selected').val();	
	
	$.ajax({
		 	type: "PUT",
	        contentType: "application/json",
	        url: "/api/book",
	        data: JSON.stringify(data),
	        success: function(res){
	        	genre.html($("#genreSelect option:selected" ).text());
	        	author.html($("#authorSelect option:selected" ).text());
	        	title.html(title.children("input[type=text]").val());
	        	buttons.html("<button id='updateBookButton' type='submit' class='btn btn-success'>Изменить книгу</button>") 
	          	alert( "Success");
			  },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            }
		});	
});


$(document).delegate('#deleteBookButton', 'click', function() {
	var parent = $(this).parent().parent();		
	var id = parent.children("td:nth-child(1)");	
	
	$.ajax({
		 	type: "DELETE",
	        contentType: "application/json",
	        url: "/api/book/" + id.html(),
	        success: function(res){
	     	   $(parent).fadeOut('slow', function(){
	               $(parent).remove();
	           }); 
			  },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            }			  
		});
	

});


$(document).delegate('#updateAuthorButton', 'click', function() {
	var parent = $(this).parent().parent();
	
	var name = parent.children("td:nth-child(2)");
	var birthDate = parent.children("td:nth-child(3)");
	var buttons = parent.children("td:nth-child(4)");
			
	name.html("<input type='text' id='name' value='" + name.html() + "'/>");
	birthDate.html("<input type='text' id='birthDate' value='" + birthDate.html() + "'/>");
	buttons.html("<button id='saveAuthorButton' type='submit' class='btn btn-success'>Save</button>&nbsp;&nbsp;<button class='btn btn-danger' id='deleteAuthorButton'>Delete</button>");
});

$(document).delegate('#saveAuthorButton', 'click', function() {
	var parent = $(this).parent().parent();
		
	var id = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");
	var birthDate = parent.children("td:nth-child(3)");
	var buttons = parent.children("td:nth-child(4)");
	
	var data = {}
	data["id"] = id.html();
	data["name"] = name.children("input[type=text]").val();
	data["birthDate"] = birthDate.children("input[type=text]").val();
		
	$.ajax({
		 	type: "PUT",
	        contentType: "application/json",
	        url: "/api/author",
	        data: JSON.stringify(data),
	        success: function(res){
	        	name.html(name.children("input[type=text]").val());
	        	birthDate.html(birthDate.children("input[type=text]").val());
	        	buttons.html("<button id='updateAuthorButton' type='submit' class='btn btn-success'>Изменить автора</button>")  
	        	},
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            }
		});
	

});

$(document).delegate('#deleteAuthorButton', 'click', function() {
	var parent = $(this).parent().parent();		
	var id = parent.children("td:nth-child(1)");		
	$.ajax({
		 	type: "DELETE",
	        contentType: "application/json",
	        url: "/api/author/"+id.html(),
	        success: function(res){
		     	   $(parent).fadeOut('slow', function(){
		               $(parent).remove();
		           }); 
				  },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            }
		});
});

$(document).delegate('#updateGenreButton', 'click', function() {
	var parent = $(this).parent().parent();
	
	var id = parent.children("td:nth-child(1)");
	var description = parent.children("td:nth-child(2)");
	var buttons = parent.children("td:nth-child(3)");
			
	description.html("<input type='text' id='description' value='" + description.html() + "'/>");
	buttons.html("<button id='saveGenreButton' type='submit' class='btn btn-success'>Save</button>&nbsp;&nbsp;<button class='btn btn-danger' id='deleteGenreButton'>Delete</button>");
});


$(document).delegate('#saveGenreButton', 'click', function() {
	var parent = $(this).parent().parent();
		
	var id = parent.children("td:nth-child(1)");
	var description = parent.children("td:nth-child(2)");
	var buttons = parent.children("td:nth-child(3)");
	
	var data = {}
	data["id"] = id.html();
	data["description"] = description.children("input[type=text]").val();
		
	$.ajax({
		 	type: "PUT",
	        contentType: "application/json",
	        url: "/api/genre",
	        data: JSON.stringify(data),
	        success: function(res){
	        	description.html(description.children("input[type=text]").val());
	        	buttons.html("<button id='updateGenreButton' type='submit' class='btn btn-success'>Изменить жанр</button>")
				  },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            }
		});  
});

$(document).delegate('#deleteGenreButton', 'click', function() {
	var parent = $(this).parent().parent();
		
	var id = parent.children("td:nth-child(1)");	
	
	$.ajax({
		 	type: "DELETE",
	        contentType: "application/json",
	        url: "/api/genre/" + id.html(),
	        success: function(res){
		     	   $(parent).fadeOut('slow', function(){
		               $(parent).remove();
		           }); 
				  },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
            }
		});
});