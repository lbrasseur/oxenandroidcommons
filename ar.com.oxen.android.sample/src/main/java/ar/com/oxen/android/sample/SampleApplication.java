package ar.com.oxen.android.sample;

import java.util.List;

import roboguice.application.GuiceApplication;

import com.google.inject.Module;

public class SampleApplication extends GuiceApplication {
	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new SampleConfigModule());
	}
}
