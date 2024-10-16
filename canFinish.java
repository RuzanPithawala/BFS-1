class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites== null || prerequisites.length==0) return true;
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        Queue<Integer> sem = new LinkedList<>();

        int [] indeg = new int[numCourses];

        for(int[] edge:prerequisites){
            indeg[edge[0]]++;

            if(!map.containsKey(edge[1])){
                map.put(edge[1],new ArrayList<>());
            }
            map.get(edge[1]).add(edge[0]);
        }
        int count=0;
        for(int i=0;i<indeg.length;i++){
            if(indeg[i]==0) {
                sem.add(i);
                count++;
            }
        }
        while(count<numCourses && !sem.isEmpty()){
            int course = sem.poll();
            if(!map.containsKey(course)) continue;
            for(int i:map.get(course)){
                indeg[i]--;
                if(indeg[i]==0){
                    sem.add(i);
                    count++;
                }
            }
        }
        if(count<numCourses) return false;
        return true;
        
    }
}
