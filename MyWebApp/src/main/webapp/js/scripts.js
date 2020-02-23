Date.prototype.today = function() {
    let yyyy = this.getFullYear().toString(),
        mm = (this.getMonth()+1).toString(),
        dd  = this.getDate().toString();

    return yyyy + (mm[1]?mm:"0"+mm[0]) + (dd[1]?dd:"0"+dd[0]);
};

Date.prototype.todayFormatted = function() {
    let yyyy = this.getFullYear().toString(),
        mm = (this.getMonth()+1).toString(),
        dd  = this.getDate().toString();

    return `${(mm[1]?mm:"0"+mm[0])}-${(dd[1]?dd:"0"+dd[0])}-${yyyy}`;
};

String.prototype.trunc = String.prototype.trunc ||  function(n) {
    return (this.length > n) ? this.substr(0, n-3) + '...' : this;
};

String.prototype.replaceAll = function(target, replacement) {
    return this.split(target).join(replacement);
};

Object.defineProperty(Array.prototype, 'chunk', {
    value: function(chunkSize){
        let temporal = [];

        for (let i = 0; i < this.length; i+= chunkSize){
            temporal.push(this.slice(i,i+chunkSize));
        }

        return temporal;
    }
});

const groupBy = (xs, key) => xs.reduce((acc, x) => Object.assign({}, acc, {
    [x[key]]: (acc[x[key]] || []).concat(x)
}), {});

//set the authorization header before each call
$.ajaxSetup({
    headers: {'Authorization': localStorage.getItem('auth-key')},
    contentType: "application/json",
    statusCode: {
        403: function(xhr) {
            if(xhr.responseText.indexOf("not authorized to access this resource") >= 1) {
                logout();
            }
        },
        401: function(xhr) {
            if(xhr.responseText.indexOf("You must be authenticated to access these resources") >= 1) {
                logout();
            }
        },
        503: () => {
            displayPlainFailure('Service unavailable')
        }
    }
});

const logout = function() {
    localStorage.removeItem('auth-key');
    localStorage.removeItem('permission-level');
    $.get("/api/v1/bypass/logout");
    window.location.replace('/bypass/');
};

function displayFailure(error, action) {
    let responseJSON = error.responseJSON || {};
    console.log(`Error status: ${error.status || "Unknown"}`);
    $("#errorMessage").text(`${action}: ${responseJSON.message || "No error message specified"}`);
    $("#errorArea").removeClass("hide");
    $("#mainLoadIndicator").addClass("hide");
}

function displayPlainFailure(error) {
    $("#errorMessage").text(error);
    $("#errorArea").removeClass("hide");
    $("#mainLoadIndicator").addClass("hide");
}

function clearError() {
    $("#errorMessage").text("");
    $("#errorArea").addClass("hide");
}

function displaySuccess(action) {
    //Clear any errors before displaying a success message
    clearError();
    $("#successMessage").text(action);
    $("#successArea").removeClass("hide");
    $("#mainLoadIndicator").addClass("hide");
}

function displaySuccessToast(action) {
    Materialize.toast(action, 1000, 'green darken-2')
}

function displayFailureToast(action, error) {
    Materialize.toast(action + ": " + error.responseJSON.message, 1000, 'red darken-2')
}

function clearSuccess() {
    $("#successMessage").text("");
    $("#successArea").addClass("hide");
}

function hideLoadBar() {
    $("#loadBar").addClass('hidden');
}

function showLoadBar() {
    $("#loadBar").removeClass('hidden');
}



//See if the user has requested permission.
//This is admittedly a little basic and naive but it should suffice our needs for the client side.
//Just get the last character of the permission level and do an integer comparision to handle the level of permission to grant
function hasPermission(required) {
    let currentLevel = localStorage.getItem('permission-level');
    if (currentLevel === undefined || currentLevel === " " || currentLevel === null) {
        displayPlainFailure("Cannot determine permission level! Please log out and log back in.. if problem persists contact Systems Integration.");
        return false;
    }
    if (currentLevel === "ADMIN") {
        return true;
    } else {
        if(required === "ADMIN" && currentLevel === "ADMIN") {
            return true;
        }
        //We get 10 levels of permissions here just getting 0-9.. If we need more than that... why?
        return (Number(currentLevel.slice(-1)) >= required);
    }
}
function updateTheme() {
    let themeables = $(".themeable"),
        currentTheme = localStorage.getItem("theme");
    if(currentTheme === "light") {
        themeables.removeClass("grey darken-4 white-text darkTheme").addClass("white lighten-5 lightTheme");
    } else {
        themeables.removeClass("white lighten-5 lightTheme").addClass("grey darken-4 white-text darkTheme");
    }
}

//For an easier on the eyes theme...
//Default theme is dark
function invertColors() {
    let themeables = $(".themeable"),
        currentTheme = localStorage.getItem("theme");

    if(currentTheme === "light") {
        localStorage.setItem("theme", "dark");

        //themeables.removeClass("blue-grey lighten-5 lightTheme").addClass("blue-grey darken-4 white-text darkTheme");
        themeables.removeClass("blue-grey lighten-5 lightTheme").addClass("grey darken-4 white-text darkTheme");
    } else {
        localStorage.setItem("theme", "light");
        themeables.removeClass("grey darken-4 white-text darkTheme").addClass("blue-grey lighten-5 lightTheme");

    }
}



function startTime() {
  let today = new Date();
  let h = today.getHours();
  let m = today.getMinutes();
  let s = today.getSeconds();
  m = checkTime(m);
  s = checkTime(s);
  document.getElementById('txt').innerHTML =
  h + ":" + m + ":" + s;
  let t = setTimeout(startTime, 500);
}
function checkTime(i) {
 if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
 return i;
}

function validate(){
          console.log("inside validate");
          if(document.getElementById("student_id").value == ""){
            console.log("ID empty");
            alert("Id is empty");
          }
          else if(document.getElementById("first_name").value == ""){
             alert("First name is empty");
          }
          else if (document.getElementById("last_name").value == ""){
            alert("Last Name is empty");
          }
          else if(document.getElementById("email").value != ""){
            if((document.getElementById("email").value).includes(" ") || !(document.getElementById("email").value).includes(".") || !(document.getElementById("email").value).includes("@")){
                alert("Email format is not correct");
            }
          }
          else{
            addStudent(document.getElementById("first_name").value,document.getElementById("last_name").value,document.getElementById("student_id").value,document.getElementById("email").value)
          }
        }