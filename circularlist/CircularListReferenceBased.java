package circularlist;

public class CircularListReferenceBased<E> implements CircularList<E> {
	private MyLinkedList<E> list;

	public CircularListReferenceBased() {
		list = new MyLinkedList<E>();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean add(E item) {
		list.add(item);
		return true;
	}

	@Override
	public void add(int index, E item) throws IndexOutOfBoundsException {
		list.add(index, item);
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		return list.remove(index);
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		return list.get(index);
	}

	@Override
	public CircularIterator<E> iterator() {
		return new CircularIterator<E>(this);
	}
}