async function loadFragment(path, containerId, fragmentId) {
  const response = await fetch(path);
  const data = await response.text();
  const fragment = document.createElement("div");
  fragment.innerHTML = data;
  const container = document.querySelector(fragmentId);
  const fragmentContainer = fragment.querySelector(containerId);
  container.appendChild(fragmentContainer);
}

// Load Navbar
export async function loadNavbarFragment() {
  await loadFragment(
    "./layout/fragment_navbar.html",
    "#navbar-container",
    "#navbar"
  );
}

// load Users list
export async function loadUsersListFragment() {
  await loadFragment(
    "./layout/fragment_users-list.html",
    "#users-list-container",
    "#users-list"
  );
}

// load User info
export async function loadUserInfoFragment() {
  await loadFragment(
    "./layout/fragment_user-info.html",
    "#user-info-container",
    "#user-info"
  );
}

// load Admin panel
export async function loadAdminPanelFragment() {
  await loadFragment(
    "./layout/fragment_admin-panel.html",
    "#admin-panel-container",
    "#admin-panel"
  );
}

// load new User Creation form
export async function loadNewUserCreationFragment() {
  await loadFragment(
    "./layout/fragment_create-user.html",
    "#create-user-container",
    "#create-user"
  );
}

// load Edit Modals
export async function loadEditModalFragment() {
  await loadFragment(
    "./layout/modals/modal_edit.html",
    "#edit-modal-container",
    "#edit-modal"
  );
}

// load Delete Modals
export async function loadDeleteModalFragment() {
  await loadFragment(
    "./layout/modals/modal_delete.html",
    "#delete-modal-container",
    "#delete-modal"
  );
}
