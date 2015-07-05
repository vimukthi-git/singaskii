This is a solution to http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/ .

## Building

ant clean jar

## Running

- Set memory options according to your environment and run,

java -jar -Xms6020m -Xmx6020m dist/SingaSkii.jar /location/to/map.txt

## Algorithm

There are two algorithms implemented in the code,
1) A single threaded version to implement an adaption of Floyd-Warshall algorithm(AdaptedFloydWarshall.java) - SLOW for large maps.
2) WhiteWalker algorithm(check WhiteWalker.java) - A multi agent based concurrent algorithm
where the each agent walks down the paths and collects results to be compared and summarized later.

## Results

With WhiteWalker algorithm in runs in about **4 seconds** for the specified 1000 by 1000 map.

Machine spec = core i5 2GHz, 8GB

Output for the given map,

Longest path: Length = 15, drop = 1422, Peaks = [1422, 1412, 1316, 1304, 1207, 1162, 965, 945, 734, 429, 332, 310, 214, 143, 0]
Email to : 151422@redmart.com
Elapsed Time= 4340ms
