
data class Book(val title: String, val author: String, val year: Int, val available: String) {
    fun borrowBook() = copy(available = "no")
    fun returnBook() = copy(available = "yes")
}

fun searchBooks(books: List<Book>, searchString: String): List<Book> {
    return books.filter { book ->
        (book.title.contains(searchString, ignoreCase = true) ||
                book.author.contains(searchString, ignoreCase = true) ||
                book.year.toString().contains(searchString)) &&
                book.available.contains("yes", ignoreCase = true)
    }
}

fun addBook(bookArray: MutableList<Book>) {
    // Adding a new book
    print("Enter the title of the new book: ")
    val newTitle = readLine().orEmpty()
    print("Enter the author of the new book: ")
    val newAuthor = readLine().orEmpty()
    print("Enter the publication year of the new book: ")
    val newYear = readLine()?.toIntOrNull() ?: 0

    val newBook = Book(newTitle, newAuthor, newYear, "yes")
    bookArray.add(newBook)

    println("New book has been added. Updated books:")
    bookArray.forEach { book ->
        println("Title: ${book.title}, Author: ${book.author}, Year: ${book.year}, Available: ${book.available}")
    }
}

fun borrowBook(bookArray: MutableList<Book>) {
    print("Enter the title of the book you want to borrow: ")
    val bookToBorrowTitle = readLine().orEmpty()

    val bookToBorrow = bookArray.find { it.title.equals(bookToBorrowTitle, ignoreCase = true) }
    if (bookToBorrow != null) {
        bookArray[bookArray.indexOf(bookToBorrow)] = bookToBorrow.borrowBook()
        println("$bookToBorrowTitle has been borrowed. Updated books:")
        bookArray.forEach { book ->
            println("Title: ${book.title}, Author: ${book.author}, Year: ${book.year}, Available: ${book.available}")
        }
    } else {
        println("Book not found.")
    }
}

fun availableBook(bookArray: List<Book>) {
    println("All available Book:")
    bookArray.forEach { book -> if( book.available.contains("yes", ignoreCase = true)){
        println("Title: ${book.title}, Author: ${book.author}, Year: ${book.year}, Available: ${book.available}")
    }}
}
fun returnBook(bookArray: MutableList<Book>) {
    println("All returnable Book:")
    bookArray.forEach { book -> if( book.available.contains("no", ignoreCase = true)){
        println("Title: ${book.title}, Author: ${book.author}, Year: ${book.year}, Available: ${book.available}")
    }}

    print("Enter the title of the book you want to return: ")
    val bookToReturnTitle = readLine().orEmpty()

    val bookToReturn = bookArray.find { it.title.equals(bookToReturnTitle, ignoreCase = true) }
    if (bookToReturn != null && bookToReturn.available.equals("no", ignoreCase = true)) {
        bookArray[bookArray.indexOf(bookToReturn)] = bookToReturn.returnBook()
        println("$bookToReturnTitle has been returned. Updated books:")
        bookArray.forEach { book ->
            println("Title: ${book.title}, Author: ${book.author}, Year: ${book.year}, Available: ${book.available}")
        }
    } else if (bookToReturn != null && bookToReturn.available.equals("yes", ignoreCase = true)) {
        println("$bookToReturnTitle is not currently borrowed.")
    } else {
        println("Book not found or not available for returning.")
    }
}
fun searchBook(bookArray: List<Book>) {
    print("Enter search string: ")
    val name = readLine().orEmpty()
    val searchString = name.trim()
    println("Search name is $name")

    val result = searchBooks(bookArray, searchString)

    if (result.isEmpty()) {
        println("No matching books found.")
    } else {
        println("Matching books:")
        result.forEach { book -> if( book.available.contains("yes", ignoreCase = true)){
            println("Title: ${book.title}, Author: ${book.author}, Year: ${book.year}, Available: ${book.available}")
        }}
    }

}



fun main() {
    var bookArray = mutableListOf(
        Book("This is a bangla book", "miraz", 2006, "no"),
        Book("This is an English book", "rakib", 2006, "yes"),
        Book("This is a bangla to english book", "miraz", 2006, "yes")
    )
    var continueLoop = true

    while (continueLoop) {
        print("Add Book: 1\nBorrow Book: 2\nDisplay available Book: 3\nSearch Book: 4\nReturn Book: 5\nExit: 0\n")
        val choice = readLine()

        when (choice) {
            "1" -> addBook(bookArray)
            "2" -> borrowBook(bookArray)
            "3" -> availableBook(bookArray)
            "4" -> searchBook(bookArray)
            "5" -> returnBook(bookArray)
            "0", "n" -> continueLoop = false
            else -> println("Invalid choice. Please enter a valid option.")
        }
    }
}


