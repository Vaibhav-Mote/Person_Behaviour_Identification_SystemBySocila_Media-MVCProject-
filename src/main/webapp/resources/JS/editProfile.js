function isUniqueUsername() {
    var username = document.getElementById("username").value;
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;

    // Validate the username
    if (!validusername(username)) {
        document.getElementById("message").innerHTML = "Invalid username: must not contain spaces and cannot be empty.";
        return false;
    }

    // Validate the email
    if (!validemail(email)) {
        document.getElementById("message").innerHTML = "Invalid email format.";
        return false;
    }

   
    
}

function validusername(username) {
    // Username should not be empty and must not contain spaces
    let pattern = /^[a-zA-Z0-9_]+$/;
    return username.trim() !== "" && pattern.test(username);
}

function validemail(email) {
    // Email validation pattern
    let pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return pattern.test(email);
}
