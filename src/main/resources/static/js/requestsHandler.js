import * as configuration from "./configuration.js";

// POST
export function postNewUserHandler() {
  const form = document.getElementById("new-user-form");
  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const data = new FormData(form);
    const roles = Array.from(data.getAll("roles"));

    const user = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      age: data.get("age"),
      email: data.get("email"),
      password: data.get("password"),
      roles: roles,
    };

    const jsonData = JSON.stringify(user);
    fetch("http://localhost:8080/api/user/new", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: jsonData,
    })
      .then(function () {
        document.querySelector("#new-user-tab").classList.remove("active");
        document
          .querySelector("#new-user-fragment")
          .classList.remove("show", "active");
        document.querySelector("#users-list-tab").classList.add("active");
        document
          .querySelector("#users-list-fragment")
          .classList.add("show", "active");
      })
      .then(function () {
        form.reset();
      })
      .then(function () {
        const usersTable = document.getElementById("users-list-container");
        usersTable.remove();
        configuration.usesListConfigurator();
      });
  });
}

// PATCH
export function patchUserHandler() {
  const form = document.getElementById("edit-form-data");
  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const data = new FormData(form);
    const roles = Array.from(data.getAll("roles"));

    const user = {
      id: data.get("id"),
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      age: data.get("age"),
      email: data.get("email"),
      password: data.get("password"),
      newPassword: data.get("newPassword"),
      roles: roles,
    };

    const jsonData = JSON.stringify(user);
    fetch(`http://localhost:8080/api/users/${user.id}/edit`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: jsonData,
    })
      .then(function () {
        form.reset();
      })
      .then(function () {
        const usersTable = document.getElementById("users-list-container");
        usersTable.remove();
        configuration.usesListConfigurator().catch((err) => {
          console.log(err);
        });
      });
  });
}

// DELETE
export function deleteUserHandler() {
  const form = document.getElementById("delete-form-data");
  form.addEventListener("submit", function (event) {
    event.preventDefault();
    console.log(form);
    const data = new FormData(form);
    const userID = data.get("id");

    fetch(`http://localhost:8080/api/users/${userID}/delete`, {
      method: "DELETE",
    })
      .then(function () {
        form.reset();
      })
      .then(function () {
        const usersTable = document.getElementById("users-list-container");
        usersTable.remove();
        configuration.usesListConfigurator().catch((err) => {
          console.log(err);
        });
      });
  });
}
