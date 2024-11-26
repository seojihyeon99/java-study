package behavioral.iterator;

public class AggregateImpl<E> implements Aggregate<E>{
	private E[] datas;
	
	public AggregateImpl(E[] datas) {
		this.datas = datas;
	}

	@Override
	public Iterator<E> createIterator() {
		return new IteratorImpl();
	}
	
	// 내부 클래스 (외부 클래스의 private에 접근할 수 있다는 장점이 있다. => getData 필요 없어짐)
	private class IteratorImpl implements Iterator<E> {
		private int index = 0;
		
		@Override
		public E next() {
			return index < datas.length ? datas[index++] : null;
		}

		@Override
		public boolean hasNext() {
			return index < datas.length;
		}
		
	}
}
