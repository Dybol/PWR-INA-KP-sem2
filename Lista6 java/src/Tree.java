/**
 * Klasa odpowiadajaca za drzewo
 *
 * @param <T> typ drzewa
 */
public class Tree<T extends Comparable<T>> {

	/**
	 * korzen
	 */
	private TreeItem<T> node;

	/**
	 * konstruktor
	 */
	public Tree() {
		node = null;
	}

	/**
	 * dodawanie elementu
	 *
	 * @param item element
	 */
	public void insert(T item) {
		node = ins(item, node);
	}

	/**
	 * wyszukiwanie elementu
	 *
	 * @param item element
	 * @return sukces
	 */
	public boolean search(T item) {
		return isElem(item, node) != null;
	}

	/**
	 * metota pomocnicza w wyszukiwaniu
	 *
	 * @param elem     element
	 * @param treeItem element drzewa
	 * @return element drzewa
	 */
	private TreeItem<T> isElem(T elem, TreeItem<T> treeItem) {
		if (treeItem == null)
			return null;
		if (elem.compareTo(treeItem.item) == 0)
			return treeItem;

		if (elem.compareTo(treeItem.item) < 0)
			return isElem(elem, treeItem.left);
		else
			return isElem(elem, treeItem.right);

	}

	/**
	 * metoda pomocnicza przy dodawaniau
	 *
	 * @param elem     element
	 * @param treeItem element drzewa
	 * @return element drzewa
	 */
	private TreeItem<T> ins(T elem, TreeItem<T> treeItem) {
		if (treeItem == null)
			return new TreeItem<>(elem);

		if (elem.compareTo(treeItem.item) < 0)
			treeItem.left = ins(elem, treeItem.left);
		else if (elem.compareTo(treeItem.item) > 0)
			treeItem.right = ins(elem, treeItem.right);

		return treeItem;
	}

	/**
	 * Metoda odpowiadajaca za usuwanie elementow
	 *
	 * @param elem element do usuniecia
	 * @return sukces danej operacji
	 */
	public boolean remove(T elem) {
		TreeItem<T> focusNode = node;
		TreeItem<T> parent = node;

		boolean isItALeftChild = true;

		while (focusNode.item != elem) {
			parent = focusNode;

			if (elem.compareTo(focusNode.item) < 0) {
				isItALeftChild = true;

				focusNode = focusNode.left;
			} else {
				isItALeftChild = false;

				focusNode = focusNode.right;
			}

			if (focusNode == null) {
				return false;
			}
		}

		if (focusNode.left == null && focusNode.right == null) {

			if (focusNode == node)
				node = null;
			else if (isItALeftChild)
				parent.left = null;
			else
				parent.right = null;
		} else if (focusNode.right == null) {

			if (focusNode == node)
				node = focusNode.left;
			else if (isItALeftChild)
				parent.left = focusNode.left;

			else
				parent.right = focusNode.left;
		} else if (focusNode.left == null) {

			if (focusNode == node)
				node = focusNode.right;

			else if (isItALeftChild)
				parent.left = focusNode.right;

			else //?
				parent.right = focusNode.right;
		} else {

			TreeItem<T> replacement = getReplacementNode(focusNode);

			if (focusNode == node)
				node = replacement;

			else if (isItALeftChild)
				parent.left = replacement;

			else
				parent.right = replacement;

			replacement.left = focusNode.left;
		}

		return true;
	}

	/**
	 * Pobieranie elementu drzewa do zastapienia usuwanego
	 *
	 * @param replacedNode element drzewa
	 * @return zastapiony element
	 */
	public TreeItem<T> getReplacementNode(TreeItem<T> replacedNode) {
		TreeItem<T> replacementParent = replacedNode;
		TreeItem<T> replacement = replacedNode;

		TreeItem<T> focusNode = replacedNode.right;

		while (focusNode != null) {
			replacementParent = replacement;

			replacement = focusNode;

			focusNode = focusNode.left;
		}

		if (replacement != replacedNode.right) {
			replacementParent.left = replacement.right;
			replacement.right = replacedNode.right;

		}

		return replacement;
	}

	/**
	 * pomocnicza metoda przy wyswietlaniu drzewa
	 *
	 * @param w - element drzewa
	 * @return sformatowane elementy
	 */
	private String toS(TreeItem<T> w) {
		if (w != null)
			return "(" + w.item + ":" + toS(w.left) + " :" + toS(w.right) + ")";
		return " () ";
	}

	/**
	 * rysuje drzewo
	 *
	 * @return drzewo
	 */
	public String draw() {
		return toS(node);
	}

	/**
	 * rysuje drzewo
	 *
	 * @return drzewo
	 */
	@Override
	public String toString() {
		return toS(node);
	}
}

/**
 * Klasa odpowiadajaca za element drzewa
 *
 * @param <T> - typ drzewa
 */
class TreeItem<T extends Comparable<T>> {
	/**
	 * wartosc elementu
	 */
	T item;

	/**
	 * wskazanie na lewe podrzewo
	 */
	TreeItem<T> left;

	/**
	 * wskazanie na prawe podrzewo
	 */
	TreeItem<T> right;

	/**
	 * Konstuktor
	 *
	 * @param item wartosc elementu
	 */
	TreeItem(T item) {
		this.item = item;
		left = null;
		right = null;
	}

	/**
	 * wyswietlanie elementu drzewa
	 *
	 * @return - sformatowany element
	 */
	@Override
	public String toString() {
		return item.toString();
	}


}
