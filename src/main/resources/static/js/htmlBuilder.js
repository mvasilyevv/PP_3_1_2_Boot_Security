// build Users list
export function buildUsersList(users) {
  let temp = "";
  users.forEach((user) => {
    temp += `<tr>
            <th scope="row">${user.id}</th>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.roles.map((role) => {
              return role.role;
            })}
            </td>
            <td><button type="button" class="btn btn-primary" data-bs-target="#user-edit-modal" 
            data-bs-id="${user.id}" data-bs-toggle="modal">Edit</button></td>

            <td><button type="button" class="btn btn-danger" data-bs-target="#user-delete-modal" 
            data-bs-id="${user.id}" data-bs-toggle="modal">Delete</button></td>
            </tr>`;
  });
  return temp;
}

// build Roles list
export function buildRolesList(roles) {
  let temp = "";
  roles.forEach((role) => {
    if (role.role === "ROLE_USER") {
      temp += `<option selected value="${role.role}">${role.role}</option>`;
    } else {
      temp += `<option value="${role.role}">${role.role}</option>`;
    }
  });
  return temp;
}

// build User Info
export function buildUserInfo(currentUser) {
  let temp = `<tr>
            <th scope="row">${currentUser.id}</th>
            <td>${currentUser.firstName}</td>
            <td>${currentUser.lastName}</td>
            <td>${currentUser.age}</td>
            <td>${currentUser.email}</td>
            <td>${currentUser.roles.join(", ")}</td>
            </tr>`;
  return temp;
}

// build User Edit Modal
export function buildEditModal(user, roles) {
  let rolesTemp = "";
  roles.forEach((role) => {
    if (user.roles.includes(role.role)) {
      rolesTemp += `<option selected value="${role.role}">${role.role}</option>`;
    } else {
      rolesTemp += `<option value="${role.role}">${role.role}</option>`;
    }
  });

  let temp = `
    <form class="form" id="edit-form-data">
      <div class="mb-1">

      <input type="hidden" name="id" value=${user.id}>
       
      <label for="edit-firstName-input" class="form-label">
         <b>First name</b>
        </label>
       <input type="text" name="firstName" class="form-control" id="edit-firstName-input" 
       value=${user.firstName}>
      </div>
    
      <div class="mb-1">
        <label for="edit-lastName-input" class="form-label">
          <b>Last name</b>
        </label>
        <input type="text" name="lastName" class="form-control" id="edit-lastName-input" 
        value=${user.lastName}>
      </div>
    
      <div class="mb-1">
        <label for="edit-age-input" class="form-label">
          <b>Age</b>
        </label>
       <input type="number" name="age" class="form-control" id="edit-age-input" 
       value=${user.age}>
      </div>
    
    <div class="mb-1">
      <label for="edit-email-input" class="form-label">
        <b>Email</b>
      </label>
      <input type="email" name="email" class="form-control" id="edit-email-input" aria-describedby="emailHelp" 
      value=${user.email}>
    </div>
    
    <div class="mb-1">
     <label for="edit-new-password-input" class="form-label">
        <b>Password</b>
      </label>
      <input type="password" class="form-control" name="newPassword" id="edit-new-password-input">
      <input type="hidden" name="password" value=${user.password}>
    </div>
    
    <div class="mb-2">
      <label for="edit-role-select" class="form-label">
       <b>Role</b>
      </label>
      <select class="form-control" name="roles" size="2" id="edit-role-select" multiple>
     ${rolesTemp}
      </select>
    </div>
    
    <div class="modal-footer">
      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Edit</button>
    </div>
    </form>
    `;

  return temp;
}

// build User Delete Modal
export function buildDeleteModal(user) {
  let rolesTemp = "";
  user.roles.forEach((role) => {
    rolesTemp += `<option value="${role}">${role}</option>`;
  });

  let temp = `
    <form class="form" id="delete-form-data">

           <input type="hidden" value="${user.id}" name="id">
            
           <div class="mb-1">
              <label th:for="deletedIDInput" class="form-label"><b>ID</b></label>
              <input type="text" class="form-control" id="deletedIDInput" disabled readonly 
              placeholder="${user.id}">
            </div>

            <div class="mb-1">
              <label for="deletedFirstNameInput" class="form-label"><b>First name</b></label>
              <input type="text" class="form-control" id="deletedFirstNameInput" disabled readonly
                placeholder="${user.firstName}">
            </div>

            <div class="mb-1">
              <label for="deletedLastNameInput" class="form-label">
                <b>Last name</b>
              </label>
              <input type="text" class="form-control" id="deletedLastNameInput" disabled readonly
                placeholder="${user.lastName}">
            </div>

            <div class="mb-1">
              <label for="deletedAgeInput" class="form-label">
                <b>Age</b>
              </label>
              <input type="number" class="form-control" id="deletedAgeInput" disabled readonly
                placeholder="${user.age}">
            </div>

            <div class="mb-1">
              <label for="deletedEmailInput" class="form-label">
                <b>Email</b>
              </label>
              <input type="email" class="form-control" id="deletedEmailInput"
                aria-describedby="emailHelp" disabled readonly 
                placeholder="${user.email}">
            </div>

            <div class="mb-2">
              <label for="deletedRoleSelect" class="form-label">
                <b>Role</b>
              </label>
              <select class="form-select" size="2" id="deletedRoleSelect" disabled>
               ${rolesTemp}
              </select>
            </div>

            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-danger" data-bs-dismiss="modal">Delete</button>
            </div>
          </form>
    `;
  return temp;
}
