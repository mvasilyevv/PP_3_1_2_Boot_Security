import * as configuration from "./configuration.js";

configuration.adminPanelConfigurator().catch((err) => {
  console.log(err);
});
configuration.newUserConfigurator().catch((err) => {
  console.log(err);
});
configuration.editModalConfigurator().catch((err) => {
  console.log(err);
});
configuration.deleteModalConfigurator().catch((err) => {
  console.log(err);
});
