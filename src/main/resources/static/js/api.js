// Get current User
export async function getCurrentUserData() {
  const response = await fetch("http://localhost:8080/api/users/current");
  const data = await response.json();

  let currentUser = {
    id: data.id,
    firstName: data.firstName,
    lastName: data.lastName,
    age: data.age,
    email: data.email,
    roles: data.roles.map((role) => {
      return role.role;
    }),
  };
  return currentUser;
}

// Get all Users
export async function getAllUsersData() {
  const response = await fetch("http://localhost:8080/api/users");
  const data = await response.json();

  let users = data.map((user) => {
    return user;
  });
  return users;
}

// Get all Roles
export async function getRolesData() {
  const response = await fetch("http://localhost:8080/api/roles");
  const data = await response.json();
  let roles = data.map((role) => {
    return role;
  });
  return roles;
}

// Get User by ID
export async function getUserDataById(id) {
  const response = await fetch(`http://localhost:8080/api/users/${id}`);
  const data = await response.json();

  let user = {
    id: data.id,
    firstName: data.firstName,
    lastName: data.lastName,
    age: data.age,
    password: data.password,
    email: data.email,
    roles: data.roles.map((role) => {
      return role.role;
    }),
  };
  return user;
}
