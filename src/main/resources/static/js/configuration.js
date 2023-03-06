import * as loadFragments from "./fragments.js";
import * as loadData from "./api.js";
import * as builder from "./htmlBuilder.js";
import * as requests from "./requestsHandler.js";

// Navbar configuration
export async function navbarConfigurator() {
  const currentUser = await loadData.getCurrentUserData();
  await loadFragments.loadNavbarFragment();
  document.getElementById("navbar-email").innerHTML = currentUser.email;
  document.getElementById("navbar-roles").innerHTML =
      "with roles: " + currentUser.roles.join(", ");
}

// Admin Panel configuration
export async function adminPanelConfigurator() {
  await loadFragments.loadAdminPanelFragment();
  await usesListConfigurator();
  await userInfoConfigurator();
}

// Admin panel: User info configuration
export async function userInfoConfigurator() {
  const currentUser = await loadData.getCurrentUserData();
  await loadFragments.loadUserInfoFragment();
  document.getElementById("current-user-data").innerHTML =
      builder.buildUserInfo(currentUser);
}

// Admin panel: Users List configuration
export async function usesListConfigurator() {
  const users = await loadData.getAllUsersData();
  await loadFragments.loadUsersListFragment();
  document.getElementById("users-list-data").innerHTML =
      builder.buildUsersList(users);
}

// New User Creation form configuration
export async function newUserConfigurator() {
  const roles = await loadData.getRolesData();
  await loadFragments.loadNewUserCreationFragment();
  document.getElementById("role-user-select").innerHTML =
      builder.buildRolesList(roles);
  requests.postNewUserHandler();
}

// Edit Modal configuration
export async function editModalConfigurator() {
  await loadFragments.loadEditModalFragment()
  const editModal = document.getElementById("user-edit-modal");
  editModal.addEventListener("show.bs.modal", async (event) => {
    const button = event.relatedTarget;
    const recipient = button.getAttribute("data-bs-id");
    let roles = await loadData.getRolesData();
    let user = await loadData.getUserDataById(recipient);
    document.getElementById("edit-modal-data").innerHTML =
        builder.buildEditModal(user, roles);
    requests.putUserHandler();
  });
}

// Delete Modal configuration
export async function deleteModalConfigurator() {
  await loadFragments.loadDeleteModalFragment()
  const deleteModal = document.getElementById("user-delete-modal");
  deleteModal.addEventListener("show.bs.modal", async (event) => {
    const button = event.relatedTarget;
    const recipient = button.getAttribute("data-bs-id");
    let user = await loadData.getUserDataById(recipient);
    document.getElementById("delete-modal-data").innerHTML =
        builder.buildDeleteModal(user);
    requests.deleteUserHandler();
  });
}
