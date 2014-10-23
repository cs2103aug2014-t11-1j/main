package gui.controller.FilteredListTextBox;

import java.util.List;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;

public class FilteredListTextBoxBehaviour<T> extends BehaviorBase<FilteredListTextBox<T>>{

	public FilteredListTextBoxBehaviour(FilteredListTextBox<T> textBox,
			List<KeyBinding> keyBindings) {
		super(textBox, keyBindings);
	}

}
