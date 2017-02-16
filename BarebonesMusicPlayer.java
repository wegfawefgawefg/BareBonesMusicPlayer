import javax.sound.midi.*;

public class BarebonesMusicPlayer
{
	public static final int CHANGE_INSTRUMENT = 192;
	public static final int NOTE_ON = 144;
	public static final int NOTE_OFF = 128;

	public static void main( String[] args )
	{
			BarebonesMusicPlayer bbmp = new BarebonesMusicPlayer();
			bbmp.play();
	}

	public void play()
	{
		try
		{
			Sequencer sequencer = MidiSystem.getSequencer();

			sequencer.setTempoInBPM( 1.0f * 60.0f );

			sequencer.open();
			Sequence sequence = new Sequence( Sequence.PPQ, 4 );
			Track instrumentChangeTrack = sequence.createTrack();
			Track noteTrackOne = sequence.createTrack();
			Track noteTrackTwo = sequence.createTrack();
			Track noteTrackThree = sequence.createTrack();
			Track noteTrackFour = sequence.createTrack();

			int instrument = 10;
			int channel = 0;

			//	change instrument of first track
			changeInstrument( channel, instrument, 0, instrumentChangeTrack );

			//	change instrument of second track
			changeInstrument( channel, instrument, 0, instrumentChangeTrack );

			//	change instrument of third track
			changeInstrument( channel, instrument, 0, instrumentChangeTrack );

			//	change instrument of fourth track
			changeInstrument( channel, instrument, 0, instrumentChangeTrack );

			//makeNote( channel, 55, 0, 7, noteTrackOne );
			//makeNote( channel, 70, 0, 2, noteTrackTwo );

			int rootNote = 55;
			int noteLength = 1;

			makeMajor7Sus(	channel, rootNote				, 1, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote	+ 2			, 2, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote	+ 7			, 3, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote	+ 11		, 4, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote + 12			, 5, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote + 12 + 2		, 6, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote	+ 12 + 7	, 7, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote	+ 12 + 11	, 8, noteLength, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);

			makeMajor7Sus(	channel, rootNote + 24			, 9, 10, 
							noteTrackOne, noteTrackTwo, 
							noteTrackThree, noteTrackFour 					);
			/*
			makeMajor7Sus( rootNote + 2		, 1, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 7		, 2, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 11		, 3, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 12		, 4, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 12 + 2	, 5, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 12 + 7	, 6, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 12 + 11	, 7, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 24		, 8, 2, trackOne, trackTwo, trackThree, trackFour );
			makeMajor7Sus( rootNote + 24 + 2	, 9, 10,trackOne, trackTwo, trackThree, trackFour );
			*/

			//	give sequence to sequencer and begin playing
			sequencer.setSequence( sequence );
			sequencer.setLoopCount( sequencer.LOOP_CONTINUOUSLY );
			sequencer.start();
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
	}


	public void changeInstrument(	int channel,
									int instrument,
									int time,
									Track track 		)
	{
		try
		{
			ShortMessage instrumentChangeMessage = new ShortMessage();
			instrumentChangeMessage.setMessage( CHANGE_INSTRUMENT, channel, instrument, 0 );
			MidiEvent changeInstrumentEvent = new MidiEvent( instrumentChangeMessage, time );
			track.add( changeInstrumentEvent );
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
	}

	public void makeNote(	int channel,
							int note,
							int time,
							int noteLength,
							Track track 		)
	{
		try
		{
			ShortMessage noteONMessage = new ShortMessage();
			noteONMessage.setMessage( NOTE_ON, channel, note, 100 );
			MidiEvent noteOnEvent = new MidiEvent( noteONMessage, time );
			track.add( noteOnEvent );

			ShortMessage noteOFFMessage = new ShortMessage();
			noteOFFMessage.setMessage( NOTE_OFF, channel, note, 0 );
			MidiEvent noteOFFEvent = new MidiEvent( noteOFFMessage, time + noteLength );
			track.add( noteOFFEvent );
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
	}

	public void makeMajor7Sus(	int channel,
								int rootNote,
								int time,
								int noteLength,
								Track trackOne,
								Track trackTwo,
								Track trackThree,
								Track trackFour		)
	{
		int secondNote = rootNote + 2;
		int thirdNote = rootNote + 7;
		int fourthNote = rootNote + 11;

		makeNote( channel, rootNote, time, noteLength, trackOne );
		makeNote( channel, secondNote, time, noteLength, trackTwo );
		makeNote( channel, thirdNote, time, noteLength, trackThree );
		makeNote( channel, fourthNote, time, noteLength, trackFour );

	}
}