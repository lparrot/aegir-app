import TaskColumnContent from "@/components/tasks/TaskColumnContent.vue";
import TaskColumnMember from "@/components/tasks/TaskColumnMember.vue";
import TaskColumnStatus from "@/components/tasks/TaskColumnStatus.vue";
import { Ref } from "vue";

const defaultColumns: AppTaskColumn[] = [
  { id: "content", header: "Contenu", headerClass: "flex flex-grow", headerStyle: {}, component: TaskColumnContent },
  { id: "assigned", header: "Assigné à", headerClass: "flex flex-shrink-0 justify-center", headerStyle: { width: "100px" }, component: TaskColumnMember },
  { id: "status", header: "Statut", headerClass: "flex flex-shrink-0 justify-center", headerStyle: { width: "150px" }, component: TaskColumnStatus },
];

const columns: Ref<AppTaskColumn[]> = ref(defaultColumns);

export function useTaskColumns() {
  return {
    columns,
  };
}
