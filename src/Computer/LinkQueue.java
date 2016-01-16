package Computer;

public class LinkQueue {
	private Node front;
	private Node rear;
	public LinkQueue(){
		front = rear = null;
	}
	public void clear(){
		front=rear=null;
	}
	public boolean isEmpty(){
		return front == null;
	}
	public int length(){
		int length =0;
		if(!isEmpty()){
			Node p = front;
			while(p!=null){
				p=p.getNext();
				++length;
			}
		}
		return length;
	}
	public Object peek(){
		if(front!=null)
		{
			return front.getData();
		}
		else
			return null;
	}
	public Object poll(){
		if(front!=null){
			Node p=front;
			front = front.getNext();
			return p.getData();
		}
		else
			return null;
	}
	public void offer(Object x){
		Node p = new Node(x);
		if(front != null){
			rear.setNext(p);
			rear=p;
		}
		else
			front = rear = p;
	}
}
