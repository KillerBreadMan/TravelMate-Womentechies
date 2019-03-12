def distance(point1, point2):
    return ((point1[0] - point2[0])**2 + (point1[1] - point2[1])**2) ** 0.5

def optimized_travelling_salesman(points, start=None):
    if start is None:
        start = points[0]
    must_visit = points
    path = [start]
    must_visit.remove(start)
    while must_visit:
        nearest = min(must_visit, key=lambda x: distance(path[-1], x))
        path.append(nearest)
        must_visit.remove(nearest)
    return path

def main():
    d_n=int(input())
    coord=[]
    for i in range(d_n):
        s=[int(input()),int(input())] #coordinate of start
        i_n=int(input()) #number of attractions
        coord.append(s)
        for j in range(i_n):
            coord.append([int(input()),int(input())]) #coordinate of each attraction
        print(optimized_travelling_salesman(coord,s))
if __name__ == "__main__":
    main()
