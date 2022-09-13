import Layout from "@/components/Layout.vue";
import LayoutLandscape from "src/components/LayoutLandscape.vue";
import type { RouteRecordRaw } from "vue-router";
import BlankView from "../components/BlankView.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/", component: LayoutLandscape, children: [
      { path: "/", name: "home", component: () => import("@/views/HomeView.vue"), meta: { landing_page: true } },
      { path: "/login", name: "login", component: () => import("@/views/LoginView.vue"), meta: { landing_page: true } },
    ],
  },
  {
    path: "/", component: Layout, children: [
      { path: "/profile", name: "profile", component: () => import("@/views/ProfileView.vue"), meta: { title: "Profil utilisateur", access: [] } },
      { path: "/tasks", name: "tasks", component: () => import("@/views/TasksView.vue"), meta: { title: "Tâches", access: [ "USER" ], with_workspaces: true } },
      {
        path: "/admin", component: BlankView, children: [
          { path: "/connected_users", name: "admin-connected-users", component: () => import("@/views/admin/ConnectedUsersView.vue"), meta: { title: "Utilisateurs connectés", access: [ "ADMIN" ] } },
          { path: "/swagger", name: "admin-swagger", component: () => import("@/views/admin/SwaggerView.vue"), meta: { title: "API", access: [ "ADMIN" ] } },
        ],
      },

      {
        path: "/dev", component: BlankView, children: [
          { path: "/accordion", name: "dev-accordion", component: () => import("@/views/dev/DevAccordionView.vue"), meta: { title: "DEV - Accordion", access: [ "USER" ] } },
          { path: "/datatable", name: "dev-datatable", component: () => import("@/views/dev/DevDatatableView.vue"), meta: { title: "DEV - Datatable", access: [ "USER" ] } },
          { path: "/loader", name: "dev-loader", component: () => import("@/views/dev/DevLoaderView.vue"), meta: { title: "DEV - Loader", access: [ "USER" ] } },
          { path: "/modal", name: "dev-modal", component: () => import("@/views/dev/DevModalView.vue"), meta: { title: "DEV - Modal", access: [ "USER" ] } },
          { path: "/toast", name: "dev-toast", component: () => import("@/views/dev/DevToastView.vue"), meta: { title: "DEV - Toast", access: [ "USER" ] } },
          { path: "/tree", name: "dev-tree", component: () => import("@/views/dev/DevTreeView.vue"), meta: { title: "DEV - Tree", access: [ "USER" ] } },
        ],
      },
      {
        path: "/errors", component: BlankView, children: [
          { name: "errors-401", path: "/errors/access_denied", component: () => import("@/views/errors/Error401View.vue") },
        ],
      },
    ],
  },
  {
    path: "/errors", component: BlankView, children: [
      { name: "errors-404", path: "/:catchAll(.*)*", component: () => import("@/views/errors/Error404View.vue"), meta: { no_match: true } },
    ],
  },
];

export default routes;
